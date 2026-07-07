package com.array;

import java.util.*;

public class UglyNumber2 {

    //YT - Code with Alisha
    public static void main(String[] args) {

    }

    public int nthUglyNumber(int n) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(1);
        int first = 1;
        int sec = 1;
        int third = 1;
        while (al.size() < n) {

            int tempF = 2 * al.get(first - 1);
            int tempS = 3 * al.get(sec - 1);
            int tempT = 5 * al.get(third - 1);

            int small = Integer.min(tempF, Integer.min(tempS, tempT));
            System.out.println("small >> " + small);

            al.add(small);

            if (tempF == small) {
                first++;
            }

            if (tempS == small) {
                sec++;
            }

            if (tempT == small) {
                third++;
            }

        }
        System.out.println(al);
        //[1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 14]
        return al.get(al.size() - 1);
    }


}
