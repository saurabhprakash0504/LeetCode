package com.sweepLine;

import java.util.*;

public class MaximumPopulationYear {

    public static void main(String[] args) {

        int[][] arr = {{1993, 1999}, {2000, 2010}, {1995, 2005}};
        MaximumPopulationYear obj = new MaximumPopulationYear();
        int result = obj.maximumPopulation(arr);
        System.out.println(result); // Output: 1995
    }

    public int maximumPopulation(int[][] logs) {
        ArrayList<Temp2> al = new ArrayList<>();
        for (int i = 0; i < logs.length; i++) {
            al.add(new Temp2(logs[i][0], 1));
            al.add(new Temp2(logs[i][1], -1));
        }

        Collections.sort(al, (a1, a2) -> {

            int val = Integer.compare(a1.year, a2.year);
            if (val == 0) {
                return Integer.compare(a1.delta, a2.delta);
            } else {
                return val;
            }
        });

        int curr = 0;
        int max = 0;
        int year = 0;
        for (int i = 0; i < al.size(); i++) {
            curr = curr + al.get(i).delta;
            if (curr > max) {
                max = curr;
                year = al.get(i).year;
            }
        }

        return year;
    }
}

class Temp2 {

    int year;
    int delta;

    Temp2(int y, int d) {
        year = y;
        delta = d;
    }
}