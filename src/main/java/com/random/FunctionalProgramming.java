package com.random;

import java.util.List;

public class FunctionalProgramming {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list.getFirst());;
        System.out.println(list.getLast());

        Character c = 'a';
        Character.isEmoji(c);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(c);
        stringBuffer.repeat(c,6);
        System.out.println(stringBuffer.toString());
    }
}
