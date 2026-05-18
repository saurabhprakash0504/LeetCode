package com.practice;

import java.util.ArrayList;

public class ComparatorExample {

    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList();
        list.add(new Student("Alice", 22));
        list.add(new Student("Dave", 22));
        list.add(new Student("Bob", 20));
        list.add(new Student("Charlie", 21));

        list.sort((student1, student2) -> {
            if (student1.getAge() != student2.getAge()) {
                return Integer.compare(student2.getAge(), student1.getAge());
            } else {
                return student1.getName().compareTo(student2.getName());
            }
        });

        for (Student student : list) {
            System.out.println(student.getName() + " - " + student.getAge());
        }
    }


}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
