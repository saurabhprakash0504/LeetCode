package com.functionalProgramming;

import java.util.Comparator;
import java.util.List;

public class AllMatch {

    public static void main(String[] args) {
        List<Courses> list = List.of(
                new Courses("Spring", "FrameWork", 91, 1000),
                new Courses("Spring1", "FrameWork1", 93, 1300),
                new Courses("Spring", "FrameWork2", 95, 1400),
                new Courses("Spring3", "FrameWork3", 18, 900),
                new Courses("Spring4", "FrameWork4", 75, 2000)
        );

        System.out.println(list.stream().allMatch(a -> a.numberOfStudents > 1000));

        System.out.println();
        list.stream().filter(a -> a.numberOfStudents > 1000).forEach(a -> System.out.println(a.name));

        System.out.println();
        list.stream().sorted((c1, c2) -> c2.score - c1.score).forEach(a -> System.out.println(a.name));

        System.out.println();
        list.stream().sorted(Comparator.comparing(Courses::getName).reversed()).forEach(a -> System.out.println(a.name));

        System.out.println();
        list.stream().sorted(Comparator.comparing(Courses::getName).reversed().thenComparing(Comparator.comparing(Courses::getNumberOfStudents).reversed())).forEach(a -> System.out.println(a.name + " " + a.numberOfStudents));
    }

}

class Courses {
    String name;
    String type;
    int score;
    int numberOfStudents;

    public Courses(String name, String type, int score, int numberOfStudents) {
        this.name = name;
        this.type = type;
        this.score = score;
        this.numberOfStudents = numberOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
}
