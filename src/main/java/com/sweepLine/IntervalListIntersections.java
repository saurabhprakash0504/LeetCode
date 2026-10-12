package com.sweepLine;

//YT - https://www.youtube.com/watch?v=Qh8ZjL1RpLI

import java.util.ArrayList;

public class IntervalListIntersections {

    public static void main(String[] args) {

        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        IntervalListIntersections obj = new IntervalListIntersections();
        int[][] result = obj.intervalIntersection(firstList, secondList);
        for (int[] interval : result) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        ArrayList<int[]> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < firstList.length && j < secondList.length) {

            if (secondList[j][0] <= firstList[i][1] && firstList[i][0] <= secondList[j][1]) {
                int start = Math.max(firstList[i][0], secondList[j][0]);
                int end = Math.min(firstList[i][1], secondList[j][1]);
                res.add(new int[]{start, end});
            }


            if (firstList[i][1] <= secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        int[][] arr = new int[res.size()][2];
        for (int ii = 0; ii < res.size(); ii++) {
            arr[ii][0] = res.get(ii)[0];
            arr[ii][1] = res.get(ii)[1];
        }
        return arr;
    }
}
