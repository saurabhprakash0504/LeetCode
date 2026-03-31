package com.practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class CodeRelay {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        CodeRelay obj = new CodeRelay();
        obj.print(list);
    }

    void print(Collection<Integer> collection) {

      //  LinkedList<Integer> list2 = new LinkedList<>(collection);
        ArrayList<Integer> list2 = new ArrayList<>(collection);
        //ArrayList<Integer> list2 = (ArrayList) collection;

        for (int i = 0; i < collection.size(); i++) {
            System.out.println(list2.get(i));
        }

    }
}
