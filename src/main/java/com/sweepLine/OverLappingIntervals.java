package com.sweepLine;

import java.util.*;

public class OverLappingIntervals {

    public static void main(String[] args) {
        int[][] arr = {{1, 3}, {2, 4}, {5, 7}, {6, 8}};
        OverLappingIntervals obj = new OverLappingIntervals();
        ArrayList<ArrayList<Integer>> result = obj.mergeOverlap(arr);
        System.out.println(result); // Output: [[1, 4], [5, 8]]
    }


    public ArrayList<ArrayList<Integer>> mergeOverlap(int[][] arr) {
        // Code here

        Arrays.sort(arr, (a1, a2) -> {
            int val = Integer.compare(a1[0], a2[0]);
            if (val == 0) {
                return Integer.compare(a2[1], a1[1]);
            }

            return val;
        });

        ArrayList<ArrayList<Integer>> al = new ArrayList<>();
        int[] prev = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int[] curr = arr[i];
            if (prev[1] >= curr[0]) {
                if (prev[1] < curr[1]) {
                    prev[1] = curr[1];
                }
            } else {
                ArrayList<Integer> tem = new ArrayList<>();
                tem.add(prev[0]);
                tem.add(prev[1]);
                al.add(tem);
                prev = curr;
            }
        }
        ArrayList<Integer> tem = new ArrayList<>();
        tem.add(prev[0]);
        tem.add(prev[1]);
        al.add(tem);

        return al;
    }

}
