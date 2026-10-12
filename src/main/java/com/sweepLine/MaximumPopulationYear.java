package com.sweepLine;

import java.util.*;

public class MaximumPopulationYear {

    public static void main(String[] args) {

        int[][] arr = {{1993, 1999}, {2000, 2010}, {1995, 2005}};
        MaximumPopulationYear obj = new MaximumPopulationYear();
        int result = obj.maximumPopulation(arr);
        System.out.println(result); // Output: 1995
    }

    public int maximumPopulation(int[][] arr) {
        ArrayList<Temp> al = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int start = arr[i][0];
            int end = arr[i][1];
            al.add(new Temp(start, true));
            al.add(new Temp(end, false));
        }

        java.util.Collections.sort(al, (t1, t2) -> {
            int indexCompare = Integer.compare(t1.ind, t2.ind);

            if (indexCompare != 0) {
                return indexCompare;
            }

            return Boolean.compare(t1.isStart, t2.isStart);
        });

        int max = 0;
        int curr = 0;
        int val = 0;
        for (int i = 0; i < al.size(); i++) {
            if (al.get(i).isStart == true) {
                curr = curr + 1;

            } else {
                curr = curr - 1;
            }
            if (max < curr) {
                val = al.get(i).ind;
                max = curr;
            }
            // max = Integer.max(curr, max);
        }

        return val;
    }
}

class Temp {

    int ind;
    boolean isStart;

    Temp(int i, boolean isS) {
        ind = i;
        isStart = isS;
    }

}