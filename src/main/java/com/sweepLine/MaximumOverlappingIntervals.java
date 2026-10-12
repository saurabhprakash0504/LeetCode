package com.sweepLine;

import java.util.ArrayList;
import java.util.Collections;

public class MaximumOverlappingIntervals {

    public static void main(String[] args) {

        int[][] arr = {{1, 3}, {2, 4}, {3, 5}};
        int result = overlapInt(arr);
        System.out.println(result); // Output: 2
    }

    public static int overlapInt(int[][] arr) {
        // code here
        ArrayList<Temp> al = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int start = arr[i][0];
            int end = arr[i][1];
            al.add(new Temp(start, true));
            al.add(new Temp(end, false));
        }

        Collections.sort(al, (t1, t2) -> {
            int indexCompare = Integer.compare(t1.ind, t2.ind);

            if (indexCompare != 0) {
                return indexCompare;
            }

            // true should come before false
            return Boolean.compare(t2.isStart, t1.isStart);
        });

        int max = 0;
        int curr = 0;
        for (int i = 0; i < al.size(); i++) {
            if (al.get(i).isStart == true) {
                curr = curr + 1;

            } else {
                curr = curr - 1;
            }
            max = Integer.max(curr, max);
        }

        return max;
    }
}
